import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { delay, Observable } from "rxjs";
import { Movie } from "./models/movie";

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  getMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.baseUrl}/movies`).pipe(delay(1000));
  }
}
