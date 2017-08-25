#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

java -Xmx8G  -cp "$DIR/*" hu.nlp.api.AppKt