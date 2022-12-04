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
  artist: Artist = undefined;
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

  private initForm() {

    let artistName = '';
    let imagePath = '';
    let pro = null;
    let ipi = '';

    if (this.editMode) {

      this.artistsService.getArtist(this.id)
        .subscribe(
          (artist: Artist) => {
            this.artist = artist;
          }
        );

      artistName = this.artist?.artistName;
      // imagePath = this.artist?.artistImageUrl;
      // pro = this.artist?.pro;
      // ipi = this.artist?.proIPI;

      console.log(artistName);

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
        .subscribe();
    } else {
      this.artistsService
        .addArtist(this.artistForm.value)
        .subscribe(
          (newArtist) => {
            console.log(newArtist);
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
