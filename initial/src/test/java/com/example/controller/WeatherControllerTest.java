/*@Bean
	public CommandLineRunner commandLineRunner(RestTemplate restTemplate) {
		return args-> {
			String res = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=London&appid=31b787379973b52a0a8581043061f3ad",String.class);
			logger.info(res);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node =objectMapper.readTree(res);
			logger.info(node.get("main").get("temp").asText());
		};
	}*/