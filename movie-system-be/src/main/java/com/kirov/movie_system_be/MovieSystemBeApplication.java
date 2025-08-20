package com.kirov.movie_system_be;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kirov.movie_system_be.genres.Genre;
import com.kirov.movie_system_be.genres.GenreRepository;
import com.kirov.movie_system_be.movies.Movie;
import com.kirov.movie_system_be.movies.MovieRepository;
import com.kirov.movie_system_be.showtimes.Showtime;
import com.kirov.movie_system_be.showtimes.ShowtimeService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class MovieSystemBeApplication implements CommandLineRunner {
  private final GenreRepository genres;
  private final MovieRepository movies;
  private final ShowtimeService showtimeService;

  public static void main(String[] args) {
    SpringApplication.run(MovieSystemBeApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    /* Seed Genres */
    Genre scifi = new Genre(null, "Sci-Fi");
    Genre crime = new Genre(null, "Crime");
    Genre action = new Genre(null, "Action");
    Genre drama = new Genre(null, "Drama");

    genres.saveAll(List.of(scifi, crime, action, drama));

    Movie inception = new Movie(null, "Inception",
        "A skilled thief who specializes in corporate espionage by infiltrating the subconscious of his targets is offered a chance to have his past crimes forgiven if he can plant an idea in someone’s mind. As the team ventures deeper into the dream world, the boundaries between reality and illusion begin to blur.",
        "https://m.media-amazon.com/images/M/MV5BMTM0MjUzNjkwMl5BMl5BanBnXkFtZTcwNjY0OTk1Mw@@._V1_.jpg", scifi);

    Movie godfather = new Movie(null, "The Godfather",
        "Spanning decades, this epic crime saga follows the powerful Corleone family as patriarch Vito Corleone navigates the dangerous world of organized crime. When his youngest son, Michael, reluctantly takes the reins, he embarks on a path of ruthless ambition and irreversible choices that will shape the family’s legacy.",
        "https://communist.red/wp-content/uploads/2022/08/godfather.png", crime);

    Movie interstellar = new Movie(null, "Interstellar",
        "In a future where Earth is dying, a group of explorers led by pilot Cooper travel through a newly discovered wormhole in search of a habitable planet. Facing the vast unknowns of space and time, they must make impossible sacrifices to ensure humanity’s survival.",
        "https://beam-images.warnermediacdn.com/BEAM_LWM_DELIVERABLES/aa5b9295-8f9c-44f5-809b-3f2b84badfbf/8a7dd34b09c9c25336a3d850d4c431455e1aaaf0.jpg?host=wbd-images.prod-vod.h264.io&partner=beamcom",
        scifi);

    Movie darkKnight = new Movie(null, "The Dark Knight",
        "Gotham City is pushed to the brink when the Joker, a sadistic criminal mastermind, unleashes chaos and challenges Batman’s moral boundaries. As the line between hero and vigilante blurs, alliances are tested and sacrifices must be made in the fight for justice.",
        "https://beam-images.warnermediacdn.com/BEAM_LWM_DELIVERABLES/52217243-a137-45d6-9c6a-0dfab4633034/74906de0-d644-4b0d-bf22-e2a321583a93?host=wbd-images.prod-vod.h264.io&partner=beamcom",
        action);

    Movie forrestGump = new Movie(null, "Forrest Gump",
        "From the football fields of Alabama to the battlefields of Vietnam, Forrest Gump unwittingly becomes part of some of America’s most historic moments. Through love, loss, and perseverance, his journey reveals that life is indeed like a box of chocolates.",
        "https://www.ebertfest.com/sites/default/files/s-l1600.jpg", drama);

    Movie pulpFiction = new Movie(null, "Pulp Fiction",
        "Told in a non-linear narrative, this modern classic weaves together the lives of two hitmen, a troubled boxer, and a pair of amateur robbers. Through sharp dialogue, dark humor, and bursts of violence, the film explores redemption, consequence, and the bizarre twists of fate.",
        "https://occ-0-8407-2219.1.nflxso.net/dnm/api/v6/E8vDc_W8CLv7-yMQu8KMEC7Rrr8/AAAABTXUxBqqR9nO9fiKrors9TwbFBD4VDIupiK1Y6HUKGui3OKiu6rStQkcI69zz7OsoLVmPvNDmSq5vhi0WpWBMnI8b8VB1-1yojF5.jpg?r=3b9",
        crime);

    movies.saveAll(List.of(inception, godfather, interstellar, darkKnight, forrestGump, pulpFiction));

    LocalDateTime now = LocalDateTime.now();
    
    for (Movie movie : List.of(inception, godfather, interstellar, darkKnight, forrestGump, pulpFiction)) {

      LocalDateTime matineeStart = now.plusDays(movie.getId()).withHour(14).withMinute(0);
      LocalDateTime matineeEnd = matineeStart.plusHours(2).plusMinutes(30);

      LocalDateTime eveningStart = now.plusDays(movie.getId()).withHour(19).withMinute(0);
      LocalDateTime eveningEnd = eveningStart.plusHours(2).plusMinutes(30);

      Showtime show1 = new Showtime(null, matineeStart, matineeEnd, "Hall 1", movie, null);
      Showtime show2 = new Showtime(null, eveningStart, eveningEnd, "Hall 2", movie, null);

      showtimeService.createShowtime(movie.getId(), show1);
      showtimeService.createShowtime(movie.getId(), show2);
    }
  }
}
