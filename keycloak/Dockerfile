FROM quay.io/keycloak/keycloak:23.0

ADD ./realms/realm-fsa.json /opt/keycloak/data/import/realm.json

RUN /opt/keycloak/bin/kc.sh import --dir=/opt/keycloak/data/import/ --override true; exit 0

ENV KEYCLOAK_ADMIN admin
ENV KEYCLOAK_ADMIN_PASSWORD admin

EXPOSE 8080

ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "-v", "start-dev", "--import-realm"]