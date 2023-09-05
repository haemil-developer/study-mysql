### 정규화
- *중복을 최소화*하게 데이터를 구조화하는 프로세스 <br>
  중복 제거하면 한 곳에서만 데이터 관리되기 때문에, 데이터 변경이 일어날 때 데이터 부정합이 일어나지 않는다.
- 쓰기의 성능을 얻기위해 읽기 성능을 어느정도 포기하는 것
  (읽을때는 항상 원본데이터를 참조하게 된다)

### 반정규화 (비)
- 읽기의 성능을 얻기위해 쓰기의 성능을 어느정도 포기 하는 것

<br>

|정규화| 반정규화               |
|---|--------------------|
|1. 중복 제거하고 한 곳에서 관리| 1. 중복 허용           |
|2. 데이터 정합성 유지가 쉬움| 2. 데이터 정합성 유지가 어려움 |
|3. 읽기시 참조 발생| 3. 참조 없이 읽기 가능     |

<br>
<br>

**정리:**
1. 테이블 설계 관점에서 조회와 쓰기의 trade-off 다.
2. 대용량 서비스 설계에 있어서 조회와 쓰기를 다르게 바라볼 수 있는 관점은 중요한 인사이트.
3. 둘 중 어떤것에 중점을 두느냐에따라 다른 아키텍쳐가 그려짐 (조회와 쓰기는 각각 상당히 다른 최적화 기법이 존재함)

<br>

- 중복된 데이터면 반드시 정규화 해야할까? **정규화도 곧 비용이다.** 읽기 비용을 지불하고 쓰기 비용을 줄이는 것
  - 얼마나 빠르게 데이터의 최신성을 보장해야 하는가? (히스토리성 데이터는 오히려 정규화 하지 않아야 한다.)
  - 데이터 변경주기와 조회 주기는 어떻게 되는가? (변경주기가 조회주기보다 빈번하다면 정규화가 훨씬 유리함.)
  - 객체(table) 탐색 깊이가 얼마나 깊은가?

<br>

- 정규화 했을때, 읽기시 데이터를 어떻게 가져올 것인가
  1. 테이블 조인 * 고민이 많이 필요함  
     서로 다른 테이블의 결합도를 엄청나게 높인다.
     따라서, 하나의 테이블에 변경이 일어날 때 여러 테이블에 영향을 받는다.
     FK 가 걸려있을 때, MySQL에서 deadlock 이슈 대두됨
  2. 조회 시 별도의 데이터베이스를 둔다: CQRS (조회용 데이터를 별도로 쌓는 방법)
  3. 캐싱을 사용한다.
  4. 읽기 쿼리가 한번 더 발생되는 것은 그렇게 큰 부담이 아닐 수 있음.