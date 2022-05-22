import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Category} from "./category.model";
import {ResponseTemplate} from "../common/model/response-template.model";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) {
  }

  getCategoryList(): Observable<Category[]> {
    return this.http.get<ResponseTemplate<Category[]>>(`${environment.apiUrl}/api/categories`)
      .pipe(map(response => response.payload))
  }
}
