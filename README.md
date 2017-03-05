# kit-service-api
[B]lck.ly's kits

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
