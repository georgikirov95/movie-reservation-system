import { Component, ViewChild, } from '@angular/core';
import { Movie } from "../models/movie";
import { MovieList } from "./movie-list/movie-list";
import { BookNowModal } from "./book-now-modal/book-now-modal";

@Component({
  selector: 'app-home',
  imports: [MovieList, BookNowModal],
  templateUrl: './home.html',
})
export class Home {
  protected categories = ['Sci-Fi', 'Crime', 'Action', 'Drama']; // example categories

  @ViewChild(BookNowModal)
  private bookNowModal?: BookNowModal;

  openBookNowModal(movie: Movie) {
    this.bookNowModal?.open(movie);
  }
}
