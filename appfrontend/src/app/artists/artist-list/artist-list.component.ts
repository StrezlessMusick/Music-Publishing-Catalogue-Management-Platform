import {Component, OnInit} from '@angular/core';
import {Artist} from "../../zshared/interfaces/artist";
import {ArtistsService} from "../../zshared/services/artists.service";
import {ActivatedRoute, Router} from "@angular/router";
import {tap} from "rxjs";

@Component({
  selector: 'app-artist-list',
  templateUrl: './artist-list.component.html',
  styleUrls: ['./artist-list.component.css']
})
export class ArtistListComponent implements OnInit {
  artists: Artist[];

  constructor(private artistsService: ArtistsService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.artistsService.getArtists()
      .pipe(
        tap(
          (artists: Artist[]) => {
            this.artists = artists;
          }
        )).subscribe();
  }

  onNewArtist() {
    this.router.navigate(
      ['new'],
      {relativeTo: this.route}
    );
  }

}
