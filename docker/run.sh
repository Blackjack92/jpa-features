docker run -d \
    --name jpa_features \
    -e POSTGRES_PASSWORD=secret \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_DB=jpa_features \
    -p 5432:5432 \
    postgres