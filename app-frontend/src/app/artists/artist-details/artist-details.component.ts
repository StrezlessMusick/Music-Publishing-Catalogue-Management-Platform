import { Component, OnInit } from '@angular/core';
import {Artist} from "../../zshared/interfaces/artist";
import {ArtistsService} from "../../zshared/services/artists.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-artist-details',
  templateUrl: './artist-details.component.html',
  styleUrls: ['./artist-details.component.css']
})
export class ArtistDetailsComponent implements OnInit {
  artist!: Artist;

  constructor(private artistsService: ArtistsService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params
      .subscribe(
        (param: Params) => {
          this.artist.id = +param['id'];
        }
      );
  }

  onEditArtist() {
    this.router.navigate(
      ['edit'],
      {relativeTo: this.route}
    );
  }

  onDeleteArtist() {
    this.artistsService.removeArtist(this.artist.id);
    this.router.navigate(
      ['../'],
      {relativeTo: this.route}
    );
  }

}
