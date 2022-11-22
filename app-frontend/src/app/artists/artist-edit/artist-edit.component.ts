import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {PRO} from "../../zshared/enums/pro";
import {ArtistsService} from "../../zshared/services/artists.service";
import {ActivatedRoute, Params, Router} from "@angular/router";

@Component({
  selector: 'app-artist-edit',
  templateUrl: './artist-edit.component.html',
  styleUrls: ['./artist-edit.component.css']
})
export class ArtistEditComponent implements OnInit {
  id: number | undefined;
  editMode = false;
  artistForm: FormGroup | any;
  pro: PRO | any;
  defaultPro = '';

  constructor(private artistsService: ArtistsService,
              private route: ActivatedRoute,
              private router: Router) {
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
    let pro = '';
    let ipi = '';

    this.artistForm = new FormGroup({
      artistName: new FormControl(artistName),
      imagePath: new FormControl(imagePath),
      pro: new FormControl(pro),
      ipi: new FormControl(ipi)
    });
  }

  onSubmit() {
    if (this.editMode) {
      this.artistsService.updateArtist(this.artistForm.value);
    } else {
      this.artistsService.addArtist(this.artistForm.value);
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
