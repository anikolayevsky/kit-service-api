# kit-service-api
[B]lck.ly's kits

#Setup
To setup, you will need to install a postgres instance with a new schema called "Kit" with owner to a user blckly.  By default (in application.properties) a connection will be made to:

spring.datasource.url=jdbc:postgresql://localhost:5432/blckly

spring.datasource.username=blckly

spring.datasource.password=blcklypw


#Usages
[B]lck.ly provides 4 endpoints for kit management.

###GET
Method used for finding kits.  Available filter parameters include sku.

/kit?sku={sku}

Returns Kit[]

###GET
Method used for fetching a kit resource.

/kit/{id}

Returns Kit

###POST
Method used for creation of a new kit.

/kit

Returns Kit

###UPDATE
Method used to update a kit.

/kit/{id}

Returns Kit
