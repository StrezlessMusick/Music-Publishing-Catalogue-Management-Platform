import {Component, OnInit, Output, ViewChild} from '@angular/core';
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
  placeHolderImage: string = 'https://i.guim.co.uk/img/media/3f6d39f213b18361c95ad0d4672ebf5680d19e7d/0_19_3500_2100/master/3500.jpg?width=620&quality=85&dpr=1&s=none'

  constructor(private artistsService: ArtistsService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit(): void {
    this.route.params
      .subscribe(
        (param: Params) => {
          this.id = +param['id'];
          console.log('Current Param ID: ' + this.id);
          // this.artist = this.artistsService.getArtist(this.id);
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
