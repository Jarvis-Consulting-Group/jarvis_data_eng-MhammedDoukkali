# Linux Cluster Monitoring Agent

# Introduction

The objective of this project was to create a functional minimum viable product (MVP) that automates the collection and storage of hardware information from a cluster of interconnected Linux machines. The target audience includes the Jarvis Linux Cluster Administration (LCA) team, as well as network administrators who require access to this data.

The project relies on a set of bash scripts that continuously collect hardware data using the crontab command. This data is then stored in a PostgreSQL Docker container, which allows for portability across different Linux machines.

To ensure efficient development and collaboration, Git was utilized as the version control system. The GitHub repository serves as a centralized location for storing and sharing project files.
Overall, this project provides a streamlined solution for gathering and managing hardware information, with potential benefits for both the LCA team and network administrators.
# Quick Start
- Start a psql instance using psql_docker.sh
`bash ./scripts/psql_docker.sh create | start | stop  [db_username] [db_password]`
- Create tables using ddl.sql
`psql -h psql_host -U psql_user -d database_name -f sql/ddl.sql`
- Insert hardware specs data into the DB using host_info.sh
`bash ./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password`
- Insert hardware usage data into the DB using host_usage.sh
`bash ./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password`
- Crontab setup
`crontab -e`
`* * * * * bash /full/path/to/linux_sql/scripts/host_usage.sh`
`psql_host port db_name psql_user psql_password &> /tmp/host_usage.log`
# Implemenation
The core of this project is the bash scripts, which are executed on host machines to gather hardware information. These scripts are elaborated upon in the "Scripts" section of the project documentation.

To facilitate the collection and storage of this data, a Docker PostgreSQL container is deployed on a designated machine. The container serves as a centralized repository for all of the collected data, which is stored in a PSQL database.

The importance and scope of the collected hardware data are discussed in greater detail elsewhere in the documentation.
## Architecture
This schematic provides an overview of the basic architecture of a Linux cluster composed of three machines. It highlights the location of the monitoring agents and the database relative to each other. The primary aim of this schematic is to showcase the practicality of deploying the MVP on a cluster, as the project was originally developed for a single-unit implementation.

## Scripts
Shell script description and usage :
- `psql_docker.sh`

1. To Create a PSQL docker container  
`bash ./scripts/psql_docker.sh create db_user db_password`

2. To start  the existing container 
`bash ./scripts/psql_docker.sh start` 

3. To stop the existing container
`bash ./scripts/psql_docker.sh stop`

- `host_info.sh`

The purpose of this shell script is to collect hardware specifications from the host machine and insert them into the PSQL database, as specified in the "Database Modeling" section. This script should be executed after ddl.sql and before host_usage.sh. To run the script, use the following command:

`bash ./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
`
- `host_usage.sh`

The purpose of this shell script is to gather usage statistics from the host machine, as outlined in the "Database Modeling" section. Prior to running this script, host_info.sh should be executed. To run the script, use the following command:

`bash ./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password`

- crontab script is used to automate the collection of usage data every minute, stores in `host_usage` and redirects the output of the script to a temporary log file.

- queries.sql (describe what business problem you are trying to resolve)

## Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)
- `host_info`

| Attribute Name | Description                              |
|----------|------------------------------------------|
| id       | Unique number to identify each ost by    |
| hostname | The name of the host machine.            |
| cpu_number | The number of CPUs in the machine.       |
| cpu_architecture | The CPU architecture.                    |
| cpu-model | The model of the CPU.                    |
| cpu_mhz  | The clock speed of the CPU in MHz.       |
| L2_cache | The size of L2 cache of the CPU in KB.   |
| total_mem | The size of the total memory in KB       |
| timestamp | Timestamp in UTC when data was collected |

 
- `host_usage`

| Attribute Name | Description                                                                                                |
|----------------|------------------------------------------------------------------------------------------------------------|
| timestamp      | Timestamp in UTC when data was collected                                                                   |
| host_id        | The same "id" number from the "host_info" table to identify each host by. It is not a superkey in this table |
| memory_free    | The size of free memory.                                                                                   |
| cpu_idle       | Percentage of processor in idle mode.	                                                                     |
| cpu-kernel     | Percentage of processor in kernel mode.                                                                    |
| disk.io        | Number of disk I/O devices.                                                                          |
| disk_available | TAvailable disk space.                                                                      |

# Test
To test the bash scripts, I used a CentOS virtual machine with Docker installed and regularly monitored the running and stopped containers. The scripts print a message indicating whether they succeeded or failed.

To execute the SQL queries, I connected to the host_agent database and retrieved records from the host_info and host_usage tables for each query.

# Improvements

- To increase the versatility of the solution, it would be beneficial to add support for other operating systems. Currently, the bash scripts and PSQL Docker image are only compatible with Linux.
- To improve the usability of the SQL script, consider including the code to create the database within the script itself, rather than requiring the user to create it manually.
- To improve the maintainability and reusability of the host_info.sh and host_usage.sh scripts, consider writing functions to encapsulate common code and avoid code duplication.