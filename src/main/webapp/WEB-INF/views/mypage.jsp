<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- css -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.2.0/css/all.min.css" integrity="sha512-6c4nX2tn5KbzeBJo9Ywpa0Gkt+mzCzJBrE1RB6fmpcsoN+b/w/euwIMuQKNyUoU/nToKN3a8SgNOtPrbW12fug==" crossorigin="anonymous" />
        <link href="/static/css/mypage.css" rel="stylesheet" type="text/css" />
        <!-- javascript -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    </head>
    <body>
    <div id="vue">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="/">Untact PhotoBooth</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="/">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Menu</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="/#portfolio">사진 찍으러가기</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="/#about">주의사항</a></li>
                                <li><a class="dropdown-item" href="/#contact">문의하기</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- 이름 출력되야함 -->
                    <form class="d-flex" v-if="existList">
                        <button class="btn btn-outline-dark" href="/mypage">
                            {{ userMail }}
                        </button>
                    </form>
                </div>
            </div>
        </nav>
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Untact Photo Booth</h1>
                    <p class="lead fw-normal text-white-50 mb-0">당신의 지난 사진들을 둘러보세요!</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <!-- 데이터 있을 때 -->
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" v-if="existList">
                    <div class="col mb-5" v-for="(history, index) in historyList" v-bind:key="history.id">
                        <div class="card h-100">
                            <!-- 카드 우측 상단 날짜 출력 -->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">{{ history.modifiedDate }}</div>
                            <!-- 분석한 사진 출력 -->
                            <img class="card-img-top" :src="history.image" alt="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" />
                            <!-- 분석한 결과 출력 -->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- 분석결과 헤드라인 -->
                                    <h5 class="fw-bolder">{{history.result}}</h5>
                                </div>
                            </div>
                            <!-- 자세히보기 누르면 다시 result 페이지로 전달되어야함. -->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent" v-for="(history, index) in historyList" v-bind:key="history.id">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="https://mynameisjunyeong.com/my-page/download/?img=history.image">download</a></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 데이터 없을 때 -->
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" v-else>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- 카드 우측 상단 날짜 출력 -->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Empty</div>
                            <!-- 분석한 사진 출력 -->
                            <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                            <!-- 분석한 결과 출력 -->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- 분석결과 헤드라인 -->
                                    <h5 class="fw-bolder">기록한 사진이 없어요!</h5>
                                </div>
                            </div>
                            <!-- 이미지 다운로드 -->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto">download</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- paging -->
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container px-4 px-lg-5">
                    <div id="paging" v-if="existList">
                        <div class="row">

                             <div class="paging">
                                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                                        <li class="nav-item" v-if="paging.pageStep > 0">
                                            <a href="#" class="nav-link" @click="goFirstPage()">first</a>
                                        </li>
                                        <li class="nav-item" v-if="!paging.first">
                                            <a href="#" class="nav-link" @click="goPrevPage()"><i class="fas fa-caret-square-left" style="font-size: 30px; color: black"></i></a>
                                        </li>
                                        <li class="nav-item" :class="{ 'on': n == paging.pageNumber + 1}" v-for="n in maxPageNumber"
                                                v-if="n >= startPageNumber">
                                            <a href="javascript:;" class="nav-link" @click="selectPage(n)"> {{ n }} </a>
                                        </li>
                                        <li class="nav-item" v-if="!paging.last">
                                            <a href="#" class="nav-link" @click="goNextPage()"><i class="fas fa-caret-square-right" style="font-size: 30px; color: black"></i></a>
                                        </li>
                                        <li class="nav-item" v-if="lastPageStep > paging.pageStep">
                                            <a href="#" class="nav-link" @click="goLastPage()">last</a>
                                        </li>
                                    <ul>
                                </div>
                             </div>

                            <div class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                                <div class="nav-link">총 {{ paging.totalElements }}개의 사진 중에서 {{ currentPageFirstIndex }} 부터 {{ currentPageLastIndex }} 까지의 사진을 현재 페이지에 담고 있습니다.</div>

                                <div class="nav-link">
                                    <select v-model="paging.pageSize" id="rowTotalNum" name="rowTotalNum" class="">
                                        <option value="10">10</option>
                                        <option value="20">20</option>
                                        <option value="50">50</option>
                                        <option value="100">100</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">사진의 양이 많다면, 숫자 또는 아이콘 버튼을 클릭하여 페이지를 이동하시거나, 셀렉트 박스를 클릭하여 한 페이지에 더 많은 사진이 표시되도록 하실 수 있습니다!</p></div>
        </footer>
    </body>

    <script>
    const userEmail = '${userEmail}';

        <!-- vue.js -->
        var vueInstance = new Vue({
            el: '#vue',
            data() {
                return {
                    paging: {   // 페이징 관련
                        pageNumber: 0,      // 페이지 번호
                        pageSize: 8,       // 페이지 당 사이즈
                        totalPages: 0,      // 총 페이지 수
                        totalElements: 0,       // 총 예약 건 수
                        pagePerPagination: 5,   // 한 페이지네이션에 보여질 페이지 갯 수
                        pageStep: 0,            // 페이지 네이션 스텝
                        last: false,    // 마지막 페이지 여부
                        first: true,    // 첫 번째 페이지 여부
                    },
                    search: {   // 검색조건
                        searchTxt: userEmail,
                    },
                    historyList: [],       // 리스트
                    existList: false,      // 불러왔을 때 값이 있는지 확인
                    userMail: userEmail,
                }
            },
            filters: {

            },
            methods: {
                // 내가 기록한 분석결과 리스트 불러오기
                getList: async function() {
                    const vuei = this;

                    // 파라미터 셋팅
                    const params = {
                        ...this.paging,
                        ...this.search
                    };
                    await axios.get('/my-page/data-list', { params: params })
                        .then(result => {
                            if (result.data.code == '0') {
                                const pageResult = result.data.pageList;
                                vuei.historyList = pageResult.content;      // 불러온 데이터 리스트
                                vuei.paging.page = pageResult.number;
                                vuei.paging.last = pageResult.last;
                                vuei.paging.first = pageResult.first;
                                vuei.paging.totalPages = pageResult.totalPages;
                                vuei.paging.totalElements = pageResult.totalElements;

                                if (pageResult.totalElements == 0) {
                                    vuei.existList = false;
                                } else {
                                    vuei.existList = true;
                                }
                            } else {
                                console.log("No Data");
                            }
                        })
                },
                imageDownload: function() {
                    const vuei = this;


                },
                // 첫 페이지로 이동
                goFirstPage: function () {
                    this.paging.pageNumber = 0;
                    this.paging.pageStep = 0;
                    this.getList();
                },

                // 마지막 페이지로 이동
                goLastPage: function () {
                    this.paging.pageNumber = this.paging.totalPages - 1;
                    this.paging.pageStep = this.lastPageStep;
                    this.getList();
                },

                // 옵션 변경 시 첫 페이지로 이동
                changeSearchOptions: function (value) {
                    this.paging.pageSize = value;
                    this.paging.pageNumber = 0;
                    this.getList();
                },

                // page 선택 이벤트
                selectPage: function (pageNumber) {
                    this.paging.pageNumber = pageNumber - 1;
                    this.getList();
                },

                // 다음페이지로 이동
                goNextPage: function () {
                    this.paging.pageNumber += 1;
                    if (this.paging.pageNumber + 1 > this.paging.pagePerPagination * (this.paging.pageStep + 1)) {
                        this.paging.pageStep++;
                    }
                    this.getList();
                },

                // 이전페이지로 이동
                goPrevPage: function () {
                    this.paging.pageNumber -= 1;    // 2
                    if (this.paging.pageNumber + 1 < this.paging.pagePerPagination * this.paging.pageStep + 1) {
                        this.paging.pageStep--;
                    }
                    this.getList();
                }
            },
            mounted() {
                this.getList();
                this.userMail = userEmail;
            },
            updated() {
                const vuei = this;
                $('#rowTotalNum').on('change', function () {
                    vuei.changeSearchOptions($(this).val());
                });
                $('#rowTotalNum').niceSelect();  // 하단 사이즈 개 수 선택
            },
            computed: {
                /* 페이지네이션에 보여질 페이지 시작 점 */
                startPageNumber: function () {
                    // 페이지 당 페이지네이션 갯 수 * (현재 페이지 스텝 + 1)
                    const startPageNumber = this.paging.pagePerPagination * this.paging.pageStep + 1;
                    return startPageNumber;
                },
                /* 페이지네이션에 보여질 페이지 종료 점 */
                maxPageNumber: function () {
                    const maxPageNumber = this.paging.pagePerPagination * (this.paging.pageStep + 1);
                    return maxPageNumber > this.paging.totalPages ? this.paging.totalPages : maxPageNumber;
                },
                /* 마지막 페이지네이션의 스텝을 계산 */
                lastPageStep: function () {
                    /**
                     * 총 페이지 개 수를 페이지네이션 당 페이지 개 수로 나눈 나머지가 0보다 크면
                     * 총 페이지 개 수 / 페이지네이션 당 페이지 개 수가 마지막 스텝 (0부터 시작하기 때문에 +1하지않음.)
                     * 나머지가 0이면 총 페이지 개 수 / 페이지네이션 당 페이지 개 수 - 1이 마지막 스텝이 된다. (0부터 시작하기때문에 -1.)
                    */
                    if (this.paging.totalPages % this.paging.pagePerPagination > 0) {
                        return parseInt(this.paging.totalPages / this.paging.pagePerPagination);
                    } else {
                        return parseInt(this.paging.totalPages / this.paging.pagePerPagination) - 1;
                    }
                },
                /* 현재 페이지의 첫 순번 */
                currentPageFirstIndex: function () {
                    return this.paging.pageNumber * this.paging.pageSize + 1;
                },
                /* 현재 페이지의 마지막 순번 */
                currentPageLastIndex: function () {
                    const calLastIndex = this.paging.pageNumber * this.paging.pageSize + parseInt(this.paging.pageSize);
                    return calLastIndex > this.paging.totalElements ? this.paging.totalElements : calLastIndex;
                }
            }
        });

    </script>
</html>
