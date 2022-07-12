FROM centos/postgresql-96-centos7                                        
																    
LABEL maintainer="Dompreh Candolp"
LABEL testenv="db setup"                                                  

RUN echo "================================================Starting installation of setup db Container========================================"

WORKDIR /opt

COPY . .

USER root


RUN echo "Running scripts =====>" && \
yum install -y dos2unix && \
dos2unix db_scripts/dbscript.sh 

RUN chmod 775 db_scripts/*.sh

# CMD ["db_scripts/wait-for-it.sh", "192.168.77.116:5432", "--", "sh", "db_scripts/dbscript.sh"]
