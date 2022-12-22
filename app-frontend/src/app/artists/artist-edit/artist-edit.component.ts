import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {PRO} from "../../zshared/enums/pro";
import {ArtistsService} from "../../zshared/services/artists.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Artist} from "../../zshared/interfaces/artist";
import {tap} from "rxjs";

@Component({
  selector: 'app-artist-edit',
  templateUrl: './artist-edit.component.html',
  styleUrls: ['./artist-edit.component.css']
})
export class ArtistEditComponent implements OnInit {
  id: number;
  editMode = false;
  artist: Artist;
  artistForm: FormGroup;
  pro = PRO;
  proKeys = [];

  constructor(private artistsService: ArtistsService,
              private route: ActivatedRoute,
              private router: Router) {
    this.proKeys = Object.keys(this.pro);
  }

  ngOnInit(): void {

    this.route.params
      .subscribe(
        (param: Params) => {
          this.id = +param['id'];
          this.editMode = +param['id'] != null;
          this.initForm()
        }
      );
  }

  private async initForm() {
    let artistId = -1;
    let artistName = '';
    let imagePath = '';
    let pro = '';
    let proIPI = '';

    if (this.editMode) {
      const artist = await this.artistsService
        .getArtist(this.id).toPromise();

      artistId = artist?.id
      artistName = artist?.artistName;
      imagePath = artist?.artistImageUrl;
      pro = artist?.pro;
      proIPI = artist?.proIPI;
    }

    this.artistForm = new FormGroup({
      artistId: new FormControl(artistId),
      artistName: new FormControl(artistName),
      artistImageUrl: new FormControl(imagePath),
      pro: new FormControl(pro),
      proIPI: new FormControl(proIPI)
    });
  }

  onSubmit() {
    if (this.editMode) {
      this.artistsService
        .updateArtist(this.artistForm.value)
        .pipe(
          tap((artist: Artist) => {
            this.artist = artist;
          })
        ).subscribe();
    } else {
      this.artistsService
        .addArtist(this.artistForm.value)
        .pipe(
          tap((newArtist: Artist) => {
            this.artist = newArtist;
          })
        ).subscribe();
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(
      ['../'],
      {relativeTo: this.route}
    );
  }
}
