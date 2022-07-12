#!/bin/sh

mkdir -p /var/lib/pgsql/9.6/data/
cp -f postgresql.conf.primary /var/lib/pgsql/9.6/data/ && \
cat /proc/meminfo >output.txt && \
sed '1!d' output.txt| cut -d ':' -f 2 | sed -e 's/^[ \t]*//' >result.txt && \
cat result.txt | cut -d ' ' -f 1 >memory.txt && \
grep -i "max_connections = " /var/lib/pgsql/9.6/data/postgresql.conf | cut -d ' ' -f 3 | cut -d '#' -f 1 >conn.txt && \
cp /var/lib/pgsql/9.6/data/postgresql.conf postgresql_copy.conf && \
python pgtune -i /var/lib/pgsql/9.6/data/postgresql.conf -o "postgresql.conf.$(date +%F_%R)" --type=Desktop --connections=$(<conn.txt) && \
rm output.txt memory.txt result.txt conn.txt && \
cp -f pg_hba.conf.primary /var/lib/pgsql/9.6/data/pg_hba.conf && \
echo "=====> Done installing Postgresql 9.6"

CMD ["postgres", "-c", "config_file=/etc/postgresql/postgresql.conf"]