#!  /bin/bash

#Setup command line arguments
psql_host=$1
port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Validate correct number of arguments
if [ "$#" -ne 5 ]; then
  echo "Illegal number of arguments. Please provide host, port, db, user, and password."
  exit 1
fi

#Parse hardware specifications and save to variables
lscpu_out=`lscpu`
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model" | awk '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache" | awk '{print $3}' | xargs)
total_mem= $(vmstat --unit M | tail -1 | awk '{print $4}')
timestamp=$(date +"%D %T") 

#Insert statement to put hardware specs into db
insert_stmt="
INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp)
VALUES ('${hostname}', '${cpu_number}', '${cpu_architecture}', '${cpu_model}', '${cpu_mhz}', '${l2_cache}', '${total_mem}', '${timestamp}');"

#Insert into PSQL db
export PGPASSWORD="${psql_password}"
psql -h "${psql_host}" -p "${port}" -d "${db_name}" -U "${psql_user}" -c "${insert_stmt}"

exit $?

