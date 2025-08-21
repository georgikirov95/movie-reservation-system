import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { delay, Observable } from "rxjs";
import { Showtime } from "./models/showtime";

@Injectable({
  providedIn: 'root'
})
export class ShowtimeService {
  private baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  getShowtimeByMovieIdAndId(movieId: number, id: number): Observable<Showtime> {
    return this.http.get<Showtime>(`${this.baseUrl}/movies/${movieId}/showtimes/${id}`).pipe(delay(1000));
  }
}
