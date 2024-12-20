document.addEventListener("DOMContentLoaded", function() {
    const ctx1 = document.getElementById('visitorChart').getContext('2d');
    const visitorChart1 = new Chart(ctx1, {
        type: 'line',
        data: {
            labels: ['일', '월', '화', '수', '목', '금', '토'],
            datasets: [{
                label: '일일 접속자 수',
                data: [5000, 1500, 1300, 3000, 2500, 5500, 7500],
                borderColor: 'rgba(102, 051, 000, 1)',
                backgroundColor: 'rgba(102, 051, 000, 0.2)',
                borderWidth: 1,
                fill: true
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    const ctx2 = document.getElementById('visitorChart2').getContext('2d');
    const visitorChart2 = new Chart(ctx2, {
        type: 'line',
        data: {
            labels: ['일', '월', '화', '수', '목', '금', '토'],
            datasets: [{
                label: '일일 예매 현황',
                data: [6000, 2000, 1750, 2400, 1240, 8000, 8600],
                borderColor: 'rgba(75, 192, 192, 1)',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderWidth: 1,
                fill: true
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});
