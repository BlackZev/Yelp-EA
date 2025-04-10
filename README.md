Bonjour Ludivine nous faisons ce ReadME car on a un soucis lors de l'execution de notre CI l'image du Back ne se build pas via docker Hub alors qu'elle se build bien en Local l'erreur qui apparait est la suivante :

YelpEaBack-1   | Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
YelpEaBack-1   | 2025-04-10T19:03:26.204Z ERROR 1 --- [YelpEaBack] [           main] o.s.b.d.LoggingFailureAnalysisReporter   :
YelpEaBack-1   |
YelpEaBack-1   | **
YelpEaBack-1   | APPLICATION FAILED TO START
YelpEaBack-1   | **
YelpEaBack-1   |
YelpEaBack-1   | Description:
YelpEaBack-1   |
YelpEaBack-1   | The bean 'corsConfig', defined in class path resource [edu/esiea/YelpEaBack/Cors/CorsConfig.class], could not be registered. A bean with that name has already been defined in URL [jar:nested:/app.jar/!BOOT-INF/classes/!/edu/esiea/YelpEaBack/Cors/CorsConfig.class] and overriding is disabled.


On a cherché partout, on ne trouve pas de classe CorsConfig. On a essayé de clean le projet Maven, essayé de rebuild l’app.jar et plein d’autres solutions, mais il y a toujours un souci disant qu’on a un doublon de CorsConfig.
De mon côté, j’avais récupéré le CorsConfig que j’ai mis dans le .java de mon application comme tu m’avais dit de le faire. Nous l’avions fait ensemble, rien d’autre, pas de trace d’un autre CorsConfig. J’imagine qu’il se génère automatiquement.
Mais avec Zion, on ne l’a pas trouvé, sauf que ça nous bloque pour la fin du projet.

Malgré tout, notre CI nous semble correct, et on a rempli (si je ne me trompe pas) toutes les conditions requises pour le projet. Si, lorsque tu vérifieras notre projet, tu trouves la solution, nous aimerions la connaître en espérant que ce problème de configuration n’impactera pas trop notre notation.
Si tu trouves le problème, Zion et moi aimerions que tu nous l’expliques afin que l’on puisse préparer un projet complet et solide pour le passage de notre certification.

Merci d’avance pour ta compréhension.
Thibaut et Zion
