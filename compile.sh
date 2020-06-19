#!/bin/bash
# set -e
# trap 'error' EXIT

# files mounted on /problems /submissions
#PROBLEMS_PATH="/problems"
#SUBMISSION_PATH="/submissions"
PROBLEMS_PATH="/problems"
SUBMISSION_PATH="/submissions"
#RESULT_PATH="/result"

# /sg/compile.sh problem_id submission_id lang time_limit memory_limit
if [ "$#" -ne 5 ]; then
    echo "Usage $0 [PROBLEM_ID] [SUBMISSION_ID] [LANG] [TIME_LIMIT] [MEMORY_LIMIT]"
    exit 1
fi
PROBLEM_ID=$1
SUBMISSION_ID=$2
LANG=$3
TIME_LIMIT=$4
MEMORY_LIMIT=$5
TIMEOUT="5"
GLOBAL_TIME_LIMIT="0"
GLOBAL_MEMORY_LIMIT="0"
WORKDIR="/temp"

#create_directories
if [[ ! -d $WORKDIR ]]; then
    mkdir $WORKDIR
fi

cleanup()
{
    rm -r $WORKDIR/*.*
}

case $LANG in
    "C")
        CC="gcc"
        FILENAME_CODE="main.c"
        FILENAME_OUT="a.out"
        ;;
    "CPP")
        CC="g++"
        FILENAME_CODE="main.cpp"
        FILENAME_OUT="a.out"
        ;;
    "Python")
        CC="python3"
        FILENAME_CODE="main"
        ;;
esac

cp "${SUBMISSION_PATH}/${SUBMISSION_ID}" "${WORKDIR}/${FILENAME_CODE}"

case $LANG in
    "C" | "CPP")
        export TIMEFORMAT=%R;
        COMPILATION_COMMAND="${CC} ${WORKDIR}/${FILENAME_CODE} -o ${WORKDIR}/${FILENAME_OUT}"
        if ${COMPILATION_COMMAND}; then 
            chmod 777 $WORKDIR/$FILENAME_OUT;
            #chroot ?.!:;$
            for INPUT_FILE in ${PROBLEMS_PATH}/${PROBLEM_ID}/*.in; do
                INPUT_NUMBER="${INPUT_FILE%%.*}"
                INPUT_NUMBER=${INPUT_NUMBER##*/}
                
                #TIME="0"
                #TIME=$(printf "%.2f" "$(time cat ${INPUT_FILE} | $WORKDIR/$FILENAME_OUT > $WORKDIR/m.out)e+03)";
                if (time cat ${INPUT_FILE} | timeout $TIMEOUT $WORKDIR/$FILENAME_OUT > $WORKDIR/m.out) &> out_time; then
                    TIME=$(head -n 1 out_time | tr -d '\n');
                    TIME=${TIME//.}
                    TIME=$(echo $TIME | sed 's/^0*//')
                    GLOBAL_TIME_LIMIT=$(( GLOBAL_TIME_LIMIT > TIME ? GLOBAL_TIME_LIMIT : TIME ))
                    #TIME="$(echo -n "$TIME"|tr -d '\n')"
                    #TIME=$(python -c "print($TIME*100)")
                    #$TIME="$(echo "$TIME" | tr -d '.')"
                    rm ./massif* 2> /dev/null;
                    valgrind --tool=massif $WORKDIR/$FILENAME_OUT < ${INPUT_FILE} &>/dev/null;
                    cat ./massif* > ${WORKDIR}/massif.out;
                    MEMORY=$(grep mem_heap_B ${WORKDIR}/massif.out | tr -dc '0-9\n' | sort -n - | tail -1)
                    #MEMORY=$(tail -n 4 ${WORKDIR}/massif.out | head -1 - | tr -d '\n')
                    #MEMORY=${MEMORY##=}
                    GLOBAL_MEMORY_LIMIT=$(( GLOBAL_MEMORY_LIMIT > MEMORY ? GLOBAL_MEMORY_LIMIT : MEMORY ))
                    if (( TIME < TIME_LIMIT )); then 
                        if (( MEMORY < MEMORY_LIMIT )); then
                            if diff -a -w $WORKDIR/m.out ${INPUT_FILE%%.*}.out &>/dev/null; then
                                echo "Correct Answer on test ${INPUT_NUMBER%%.*} with time ${TIME} man";
                            else
                                echo "WA:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}";
                                echo -n "WA:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}" > "${SUBMISSION_PATH}/${SUBMISSION_ID}_result";
                                exit 1 
                            fi
                        else
                            echo "MLE:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}";
                            echo -n "MLE:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}" > "${SUBMISSION_PATH}/${SUBMISSION_ID}_result";
                            exit 1
                        fi 
                    else
                        echo "TLE:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}";
                        echo -n "TLE:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}" > "${SUBMISSION_PATH}/${SUBMISSION_ID}_result";
                        exit 1
                    fi               
                else
                    echo -n "RTE:${INPUT_NUMBER%%.*}";
                    echo -n "RTE:${INPUT_NUMBER%%.*}" > "${SUBMISSION_PATH}/${SUBMISSION_ID}_result";            
                    exit 1 
                fi
            done
            echo "Accepted:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}"
            SUBMISSION_PATH="/submissions"
            echo -n "Accepted:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}" > "${SUBMISSION_PATH}/${SUBMISSION_ID}_result";
            exit 1 
        else 
            echo -n "CE";
            echo -n "CE" > "${SUBMISSION_PATH}/${SUBMISSION_ID}_result";            
            exit 1 
        fi
        ;;
    "Python")
        export TIMEFORMAT=%R;
        for INPUT_FILE in ${PROBLEMS_PATH}/${PROBLEM_ID}/*.in; do
            INPUT_NUMBER="${INPUT_FILE%%.*}"
            INPUT_NUMBER=${INPUT_NUMBER##*/}
            #TIME="0"
            #TIME=$(printf "%.2f" "$(time cat ${INPUT_FILE} | $WORKDIR/$FILENAME_OUT > $WORKDIR/m.out)e+03)";
            if (time (cat ${INPUT_FILE} | timeout $TIMEOUT $CC $WORKDIR/${FILENAME_CODE} > $WORKDIR/m.out)) &> out_time; then
                TIME=$(head -n 1 out_time | tr -d '\n');
                TIME=${TIME//.}
                TIME=$(echo $TIME | sed 's/^0*//')
                GLOBAL_TIME_LIMIT=$(( GLOBAL_TIME_LIMIT > TIME ? GLOBAL_TIME_LIMIT : TIME ))
                #TIME="$(echo -n "$TIME"|tr -d '\n')"
                #TIME=$(python -c "print($TIME*100)")
                #$TIME="$(echo "$TIME" | tr -d '.')"
                rm ./massif* 2> /dev/null;
                valgrind --tool=massif $CC $WORKDIR/$FILENAME_OUT < ${INPUT_FILE} &>/dev/null;
                cat ./massif* > ${WORKDIR}/massif.out;
                MEMORY=$(grep mem_heap_B ${WORKDIR}/massif.out | tr -dc '0-9\n' | sort -n - | tail -1)
                #MEMORY=$(tail -n 4 ${WORKDIR}/massif.out | head -1 - | tr -d '\n')
                #MEMORY=${MEMORY##=}
                GLOBAL_MEMORY_LIMIT=$(( GLOBAL_MEMORY_LIMIT > MEMORY ? GLOBAL_MEMORY_LIMIT : MEMORY ))
                if (( TIME < TIME_LIMIT )); then 
                    if (( MEMORY < MEMORY_LIMIT )); then
                        if diff -a -w $WORKDIR/m.out ${INPUT_FILE%%.*}.out &>/dev/null; then
                            echo "Correct Answer on test ${INPUT_NUMBER%%.*} with time ${TIME} man";
                        else
                            echo "WA:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}";
                            echo -n "WA:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}" > "${SUBMISSION_PATH}/${SUBMISSION_ID}_result";
                            exit 1
                        fi
                    else
                        echo "MLE:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}";
                        echo -n "MLE:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}" > "${SUBMISSION_PATH}/${SUBMISSION_ID}_result";
                        exit 1
                    fi 
                else
                    echo "TLE:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}";
                    echo -n "TLE:${INPUT_NUMBER%%.*}:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}" > "${SUBMISSION_PATH}/${SUBMISSION_ID}_result";
                    exit 1
                fi  
            else
                echo -n "RTE:${INPUT_NUMBER%%.*}";
                echo -n "RTE:${INPUT_NUMBER%%.*}" > "${SUBMISSION_PATH}/${SUBMISSION_ID}_result";            
                exit 1 
            fi             
        done
        echo "Accepted:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}"
        echo "Accepted:${GLOBAL_TIME_LIMIT}:${GLOBAL_MEMORY_LIMIT}" > "${SUBMISSION_PATH}/${SUBMISSION_ID}_result";
        exit 1
        ;;
esac