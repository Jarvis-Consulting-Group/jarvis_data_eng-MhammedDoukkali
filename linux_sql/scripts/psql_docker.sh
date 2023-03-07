
#! /bin/sh

cmd=$1
db_username=$2
db_password=$3

sudo systemctl status docker || systemctl

docker container inspect jrvs-psql
container_status=$?

case $cmd in
 create)

	if[ $container_status -eq 0 ]; then
	  echo 'Container already exists'
	  exit 1
	fi 

 	if [$# -ne 3 ]; then 
	  echo 'Create requires username and password'
	  exit 1
	fi

	docker volume create --name jrvs-psql
	
	docker run --name jrvs-psql \
	     -e POSTGRES_PASSWORD=$PASSWORD \ 
	     -e POSTGRES_USER=${PG_USER} \
	     -d
	     -v pgdata:/var/lib/postgresql/data \
	     -p 5432:5432 \
	     postgres
	exit $?
	;;

	start|stop)

	if [ $container_status=exited -f name=jrvs-psql ]; then
	  echo 'Container has not been created'
	  exit 1

	docker container $cmd jrvs-psql
	exit $?
	;;

	*)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac
					
