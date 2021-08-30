# querydslbasic
test 케이스 controller -> 단위에서 전체로

nohup /home/ec2-user/amazon-corretto-16.0.2.7.1-linux-aarch64/bin/java -jar /home/ec2-user/file.jar --spring.profiles.active=product -sever -XX:+UseZGC -Xms10G -Xmx10G -XX:+UseLargePages -Xlog:gc*:gc.log 1> /dev/null 2>&1 &

hibernate.query.in_clause_parameter_padding=true 이걸 사용하세요 ㅠㅠ 그냥 올린건데 기본이잖아요
