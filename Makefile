dependencies:
	docker run --name prometheus --rm -d -v ${PWD}/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml -p 9090:9090 prom/prometheus
	docker run --name grafana --rm -d -p 3000:3000 grafana/grafana