FROM centos
MAINTAINER chances-gaoyx
VOLUME [ "/sys/fs/cgroup" ]
RUN  /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN /bin/echo -e "ZONE="Asia/Shanghai"\nUTC=false\nRTC=false" > /etc/sysconfig/clock
RUN yum -y install epel-release; yum clean all; yum makecache ; yum -y install nginx
RUN mv /etc/nginx/nginx.conf /etc/nginx/nginx.conf.bak
ADD ./default.jpg  /usr/share/nginx/html/
ADD ./nginx.conf  /etc/nginx/
RUN mkdir -p /usr/share/nginx/logs
VOLUME [ "/opt/product/data/" ]
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
