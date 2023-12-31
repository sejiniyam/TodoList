const date = new Date();

const prevMonth = () => {
    date.setMonth(date.getMonth() - 1);
    renderCalendar();
}

const nextMonth = () => {
    date.setMonth(date.getMonth() + 1);
    renderCalendar();
}

const renderCalendar = () => {
        // 년도, 월 추출
        const viewYear = date.getFullYear();
        const viewMonth = date.getMonth();

        // 랜더링 시 년도, 월 출력
        document.querySelector('.year-month').textContent = `${viewYear}년 ${viewMonth + 1}월`;

        const prevLast = new Date(viewYear, viewMonth, 0); // 지난 달의 마지막 날 객체 생성
        const thisLast = new Date(viewYear, viewMonth + 1, 0); // 이번 달의 마지막 날 객체 생성

        const PLDate = prevLast.getDate(); // 지난 달의 마지막 날짜
        const PLDay = prevLast.getDay(); // 지난 달의 마지막 요일

        const TLDate = thisLast.getDate(); // 현재 달의 마지막 날짜
        const TLDay = thisLast.getDay(); // 현재 달의 마지막 요일

        // 날짜들 담을 배열
        const prevDates = [];
        const thisDates = [...Array(TLDate + 1).keys()].slice(1); // 1부터 TLDate까지의 숫자를 담는다.
        const nextDates = [];

        // 지난 달의 마지막 요일이 토요일(6)이라면, 그릴 필요가 없다.
        if (PLDay !== 6){
            for (let i = 0; i < PLDay + 1; i++){
                prevDates.unshift(PLDate - i);
            }
        }
        // 이번 달 마지막 날짜의 요일을 기준으로 필요한 개수를 파악해서 1부터 1씩 증가시킨다.
        for (let i = 1; i < 7 - TLDay; i++) {
            nextDates.push(i);
        }
        // Dates 합치기
        const dates = prevDates.concat(thisDates, nextDates);

        // Dates 정리
        const firstDateIndex = dates.indexOf(1);
        const lastDateIndex = dates.lastIndexOf(TLDate);
        dates.forEach((date, i) => {
            const condition = i >= firstDateIndex && i < lastDateIndex + 1
                ? 'this'
                : 'other';
            dates[i] = `<div class="date"><span class="${condition}">${date}</span></div>`;
        })

        // Dates 그리기
        document.querySelector('.dates').innerHTML = dates.join('');

        // 오늘 날짜 그리기
        const today = new Date();
        if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) {
            for (let date of document.querySelectorAll('.this')) {
                if (+date.innerText === today.getDate()) {
                    date.classList.add('today');
                    break;
                }
            }
        }

}