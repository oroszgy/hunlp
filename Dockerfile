FROM java:8

WORKDIR /app
COPY ./bin/* /app/

EXPOSE 9090
CMD /app/hunlp.sh