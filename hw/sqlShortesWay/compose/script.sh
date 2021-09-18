#systemctl start docker
# sudo netstat -ltup  вывод всех открытых портов
docker-compose -f compose-test.yaml up
#docker-compose -f compose-test.yaml up -d


#psql -h  172.18.0.1  -p 5444 -U rttr db_rudenko_first


#Останавливаем все запущенные контейнеры docker kill $(docker ps -q).
#Удаляем все остановленные контейнеры docker rm $(docker ps -a -q).
#Удаляем все образы docker rmi $(docker images -q)
