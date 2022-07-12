FROM centos/postgresql-96-centos7

LABEL maintainer="Prince Oppong Kyekyeku"                             

RUN echo "================================================Starting installation of Postgres Container========================================"


WORKDIR /installer_files
COPY . .

USER root



RUN cp -f postgresql.conf.primary /var/lib/pgsql/data/postgresql.conf && \
cat /proc/meminfo >output.txt && \
sed '1!d' output.txt| cut -d ':' -f 2 | sed -e 's/^[ \t]*//' >result.txt && \
cat result.txt | cut -d ' ' -f 1 >memory.txt && \
grep -i "max_connections = " /var/lib/pgsql/data/postgresql.conf | cut -d ' ' -f 3 | cut -d '#' -f 1 >conn.txt && \
cp /var/lib/pgsql/data/postgresql.conf postgresql_copy.conf && \
python pgtune -i /var/lib/pgsql/data/postgresql.conf -o "postgresql.conf.$(date +%F_%R)" --type=Desktop --connections=$(<conn.txt) && \
rm output.txt memory.txt result.txt conn.txt && \
echo "=====> Done installing Postgresql 9.6"

RUN echo "Running scripts =====>" && \
cp -f pg_hba.conf.primary /var/lib/pgsql/data/pg_hba.conf && \
chmod +x changeownership.sh && \
chown -R postgres:postgres /var/lib/pgsql/data/ && \
chmod -R 775 /var/lib/pgsql/data/



RUN echo "================================================Done installation of Postgres Container========================================"

USER postgres
