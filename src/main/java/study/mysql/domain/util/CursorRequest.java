package study.mysql.domain.util;

/*
    - cursor key 선정
    1. 인덱스 설정이 있어야 한다.
    2. 중복 데이터가 없어야 한다.
    3. 사용하는 키와 정렬 순서가 반드시 일치해야 한다.
*/
public record CursorRequest(Long key, int size) {
    public static final Long NONE_KEY = -1L;

    public Boolean hasKey() {
        return key != null;
    }

    public CursorRequest next(Long key) {
        return new CursorRequest(key, size);
    }
}
