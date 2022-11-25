import {Component, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Artist} from "../../zshared/interfaces/artist";
import {ArtistsService} from "../../zshared/services/artists.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Observable} from "rxjs";
import {ArtistListEntryComponent} from "../artist-list/artist-list-entry/artist-list-entry.component";
import {ArtistsComponent} from "../artists.component";

@Component({
  selector: 'app-artist-details',
  templateUrl: './artist-details.component.html',
  styleUrls: ['./artist-details.component.css']
})
export class ArtistDetailsComponent implements OnInit {
  @ViewChild('artist')artist!: Artist;
  id!: number;

  constructor(private artistsService: ArtistsService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit(): void {
    this.route.params
      .subscribe(
        (param: Params) => {
          this.id = param['id'];
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
    this.artistsService.removeArtist(this.id);
    this.router.navigate(
      ['../'],
      {relativeTo: this.route}
    );
  }

}
