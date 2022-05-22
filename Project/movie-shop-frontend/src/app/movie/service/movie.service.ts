import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Movie, MovieList} from "../model/movie.model";
import {map, Observable} from "rxjs";
import {ResponseTemplate} from "../../common/model/response-template.model";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private http: HttpClient) {
  }

  getMovieList(page: number = 0, category?: number): Observable<MovieList> {
    let queryParams = new HttpParams()
    queryParams = queryParams.append('page', page)
    if (category !== undefined) {
      queryParams = queryParams.append('category', category)
    }
    return this.http.get<ResponseTemplate<MovieList>>(`${environment.apiUrl}/api/movies`, {params: queryParams})
      .pipe(map(response => response.payload))
  }

  getMovieDetails(movieId: number): Observable<Movie> {
    return this.http.get<ResponseTemplate<Movie>>(`${environment.apiUrl}/api/movies/${movieId}`)
      .pipe(map(response => response.payload))
  }
}


