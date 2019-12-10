# WeatherAppv4
TODO

Zminić parsowanie JSON-a na takie prawilne (do obiektu DTO)

Uruchamia się na http://localhost:8080/weathergui. Dane pogodowe są pobierane z serwisu https://www.metaweather.com/api/ o pełnej godzinie każdego dnia lecz dane w serwisie aktualizowane są kilka razy dziennie (prawdopodobnie 5 razy) więc w ciągu całego dnia przez klika nastepujących po sobie godzin wpisy będą się powtarzały. Ze względu na powyższe, dane w bazie danych zapisywane są z dwoma datami tzn. z datą i godziną faktycznego utworzenia pomiarów w serwisie pogodowym (według tego co podaje sewis) oraz z datą i godziną wykonania requestu a tym samym zapisania danych w bazie danych. Na GUI dane pobierane są według daty i godziny wykonania requestu.
