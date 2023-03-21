#! /bin/bash

# Get command line arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Validate arguments
if [ "$#" -ne 5 ]; then
	echo "Illegal number of parameters"
	exit 1
fi 

#Save machine stat in MB + curr machine hostname to var
vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)
memory_free=$(vmstat --unit M | tail -1 | awk -v col="4" '{print $col}')
cpu_idle=$(echo "$vmstat_out" | awk '{print $14}' | tail -1)
cpu_kernel=$(echo "$vmstat_out" | awk '{print $15}' | tail -1)
disk_io=$(vmstat --unit M -d | tail -1 | awk -v col="10" '{print $col}')
disk_available=$( (df -m /)  | awk '{print $4}' | xargs)

#Insert statement to put usage info into the db
insert_stmt="
INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
VALUES ('${timestamp}',
(SELECT id AS host_id FROM host_info WHERE hostname = '${hostname}'),
'${memory_free}', '${cpu_idle}', '${cpu_kernel}', '${disk_io}', '${disk_available}');"

#Insert into PSQL db
export PGPASSWORD="${psql_password}"
psql -h "${psql_host}" -p "${port}" -d "${db_name}" -U "${psql_user}" -c "${insert_stmt}"

exit $?

