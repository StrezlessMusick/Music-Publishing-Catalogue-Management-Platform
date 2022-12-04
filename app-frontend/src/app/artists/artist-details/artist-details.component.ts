import {Component, OnInit} from '@angular/core';
import {Artist} from "../../zshared/interfaces/artist";
import {ArtistsService} from "../../zshared/services/artists.service";
import {ActivatedRoute, Params, Router} from "@angular/router";

@Component({
  selector: 'app-artist-details',
  templateUrl: './artist-details.component.html',
  styleUrls: ['./artist-details.component.css']
})
export class ArtistDetailsComponent implements OnInit {
  artist: Artist;
  id: number;

  constructor(private artistsService: ArtistsService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit(): void {

    this.route.params
      .subscribe(
        (param: Params) => {
          this.id = +param['id'];
          this.artistsService.getArtist(this.id)
            .subscribe(
              (artist: Artist) => {
                this.artist = artist;
              }
            );
        }
      );
  }

  onAddTrack() {
    this.router.navigate(
      ['tracks/new']
    )
  }

  onEditArtist() {
    this.router.navigate(
      ['edit'],
      {relativeTo: this.route}
    );
  }

  onDeleteArtist() {
    this.artistsService.removeArtist(this.id)
      .subscribe();
    this.router.navigate(
      ['../'],
      {relativeTo: this.route}
    );
  }

}
