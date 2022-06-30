dependencies:
	docker run --name prometheus --rm -d -v ${BASE_PATH}/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml -p 9090:9090 prom/prometheus