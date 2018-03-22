# wso2JSF
ejemplo de cliente wso2  en jsf MAVEN
# configuracion
proyecto_name/src/main/resources/travelocity.properties
```
### A unique identifier for this SAML 2.0 Service Provider application id aplication
SAML2.SPEntityId=ejemploWso2-1
### The URL of the SAML 2.0 Assertion Consumer-- your host aplication 
SAML2.AssertionConsumerURL=http://35.196.102.126:8080/ejemploWso2-1/home.xhtml
### The URL of the SAML 2.0 Identity Provider --server wso2 identity server 
SAML2.IdPURL=https://ayungan.me:9443/samlsso
### OAuth2 token endpoint URL --server wso2 identity server
OAuth2.TokenURL=https://ayungan.me:9443/oauth2/token
### OpenId Provider Url --server wso2 identity server
OpenId.ProviderURL=https://ayungan.me:9443/openid/
### openid.return_to parameter -- your host aplication 
OpenId.ReturnToURL=http://35.196.102.126:8080/ejemploWso2-1/home.xhtml
```
## estas configuraciones se realizaron para el WSO2 Identity Server 5.5.0 - RC2
servidor: https://ayungan.me:9443
app cliente :http://35.196.102.126:8080/ejemploWso2-1/home.xhtml
## solo si ejecuta localmente  opcional
#### es necesario crear un dominio para la aplicacion del cliente para q  funcione correctamente las cookies en: 
si ejecuta localmente puede realizarlo de la siguiente manera
**linux** sudo nano /etc/hosts 
***windows*** C:\Windows\System32\drivers\etc\hosts  
ejemplo: 
```
127.0.0.1  wso2is.local 
```
## compilacion del proyecto
```
mvn package
```
##### la ruta del .war esta en el dirrectrio proyecto_name/target
#### para mas informacion y configuracioon del WSO2 Indentity Server https://docs.wso2.com/display/IS550/Configuring+Single+Sign-On
