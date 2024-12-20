<template>
  <div>
    <h3>주문 정보 관리</h3>
    <hr />

    <br />
    <br />

    <h3>주문 리스트 검색</h3>

    <table class="table table-bordered table-sm" style="width:70vw;">
      <tbody>
        <tr>
          <th scope="row" class="text-center align-middle">검색어</th>
          <td>
            <div class="row align-items-center">
              <div class="col-auto">
                <select
                  class="form-select form-select"
                  style="width: 200px"
                  aria-label=".form-select example"
                >
                  <option selected>검색어 선택</option>
                  <option value="1">주문번호</option>
                  <option value="2">결제방식</option>
                </select>
              </div>
              <div class="col">
                <input
                  type="text"
                  class="form-control"
                  placeholder="검색어를 입력하세요"
                />
              </div>
            </div>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="text-center">
      <button type="button" class="btn btn-primary btn-sm me-2">검색</button>
      <button type="button" class="btn btn-secondary btn-sm">초기화</button>
    </div>

    <br />
    <br />

    <h3>주문 정보 리스트</h3>
    <hr style="width:70vw;">

    <table class="table" style="width:70vw;">
      <thead>
        <tr class="table-secondary">
          <th scope="col">주문코드</th>
          <th scope="col">결제금액</th>
          <th scope="col">결제날짜</th>
          <th scope="col">취소날짜</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td><a href="/AdminOrderDetail">[주문코드]</a></td>
          <td>[결제금액]</td>
          <td>[결제날짜]</td>
          <td>[취소날짜]</td>
        </tr>
      </tbody>
    </table>

    <br />
    <br />

    <!-- 페이지네이션 -->
    <div style="margin-left: -80px;">
      <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>

    <br />
    <br />
  </div>
</template>


<script>
export default {};
</script>
<style></style>
