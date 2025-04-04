#!/usr/bin/env bash
# wait-for-it.sh
# Use this script to test if a given TCP host/port are available.
# Inspired by https://github.com/vishnubob/wait-for-it
#
# Usage: wait-for-it.sh host:port [-t timeout] [-- command args]
#
# Example:
#   ./wait-for-it.sh eureka-server:8761 -t 30 -- echo "Eureka is up"

set -e

TIMEOUT=15
QUIET=0
WAITFORIT_cmdname=$(basename "$0")

echoerr() {
  if [ "$QUIET" -ne 1 ]; then
    echo "$@" 1>&2
  fi
}

usage() {
  cat << USAGE >&2
Usage:
  $WAITFORIT_cmdname host:port [-t timeout] [-- command args]
USAGE
  exit 1
}

wait_for() {
  local hostport=$1
  local timeout=$2
  local start_ts=$(date +%s)
  local end_ts=$((start_ts + timeout))
  while true; do
    if nc -z "${hostport%:*}" "${hostport#*:}" >/dev/null 2>&1; then
      break
    fi
    sleep 1
    local now_ts=$(date +%s)
    if [ $now_ts -ge $end_ts ]; then
      echoerr "$WAITFORIT_cmdname: Timeout occurred after waiting $timeout seconds for $hostport"
      exit 1
    fi
  done
}

if [ $# -lt 1 ]; then
  usage
fi

hostport=$1
shift

if [ -z "$hostport" ]; then
  usage
fi

while [ $# -gt 0 ]; do
  case "$1" in
    -q|--quiet)
      QUIET=1
      shift 1
      ;;
    -t)
      TIMEOUT="$2"
      if [ -z "$TIMEOUT" ]; then break; fi
      shift 2
      ;;
    --)
      shift
      break
      ;;
    *)
      echoerr "Unknown argument: $1"
      usage
      ;;
  esac
done

wait_for "$hostport" "$TIMEOUT"

if [ $# -gt 0 ]; then
  exec "$@"
fi
