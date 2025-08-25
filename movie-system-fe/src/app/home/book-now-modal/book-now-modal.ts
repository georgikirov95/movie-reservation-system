import { Component, ElementRef, inject, ViewChild } from '@angular/core';
import { Movie } from "../../models/movie";
import { Showtime } from "../../models/showtime";
import { Observable } from "rxjs";
import { ShowtimeService } from "../../showtime-service";
import { AsyncPipe, DatePipe } from "@angular/common";
import { Loading } from "../../shared/loading/loading";
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-book-now-modal',
  imports: [AsyncPipe, DatePipe, Loading, RouterLink],
  templateUrl: './book-now-modal.html',
})
export class BookNowModal {
  protected movie: Movie | undefined;
  protected selectedShowtime: Showtime | undefined;
  protected showtimes$!: Observable<Showtime[]>;
  private showtimeService = inject(ShowtimeService);

  @ViewChild('dialog') private dialogRef: ElementRef<HTMLDialogElement> | undefined;

  open(movie: Movie) {
    this.movie = movie;
    this.selectedShowtime = undefined;
    this.showtimes$ = this.showtimeService.getShowtimesByMovieId(movie.id);
    this.dialogRef?.nativeElement.showModal();
  }

  selectShowtime(showtime: Showtime) {
    this.selectedShowtime = showtime;
  }
}
