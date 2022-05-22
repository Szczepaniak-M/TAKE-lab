package pl.put.movieshop.common.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonRequestVariable {
    public static final int EMPTY_QUERY_PARAM = -1;
    public static final String MOVIE_ID_PATH_PARAM = "movieId";
    public static final String CATEGORY_QUERY_PARAM = "category";
    public static final String PAGE_QUERY_PARAM = "page";
    public static final String PAGE_SIZE_QUERY_PARAM = "pageSize";
}