import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {PRO} from "../../zshared/enums/pro";
import {ArtistsService} from "../../zshared/services/artists.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Artist} from "../../zshared/interfaces/artist";

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
          this.editMode = param['id'] != null;
          this.initForm()
        }
      );
  }

  private async initForm() {

    let artistName = '';
    let imagePath = '';
    let pro = null;
    let ipi = '';

    if (this.editMode) {
      const artist = await this.artistsService
        .getArtist(this.id).toPromise();
      artistName = artist?.artistName;
      imagePath = artist?.artistImageUrl;
      pro = artist?.pro;
      ipi = artist?.proIPI;

    }

    this.artistForm = new FormGroup({
      artistName: new FormControl(artistName),
      artistImageUrl: new FormControl(imagePath),
      pro: new FormControl(pro),
      proIPI: new FormControl(ipi)
    });
  }

  onSubmit() {
    if (this.editMode) {
      this.artistsService
        .updateArtist(this.artistForm.value)
        .subscribe(
          (artist: Artist) => {
            this.artist = artist;
          }
        );
    } else {
      this.artistsService
        .addArtist(this.artistForm.value)
        .subscribe(
          (newArtist) => {
            this.artist = newArtist
          }
        );
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
