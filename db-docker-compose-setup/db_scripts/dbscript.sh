#!/bin/bash


set -e

echo "=============setting up db users=========================" && \
export PGPASSWORD="postgres" && \

 psql -U postgres -h postgres <<EOF
           \i db_scripts/create_pg_user.sql

EOF





echo "=============creating dronesDb =========================" && \
export PGPASSWORD="droneTestPass" && \

psql -U dronetestuser -h postgres <<EOF
           \i db_scripts/dronesmanag.sql
                
EOF

