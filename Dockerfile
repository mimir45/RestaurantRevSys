FROM ubuntu:latest
LABEL authors="mimir"

ENTRYPOINT ["top", "-b"]