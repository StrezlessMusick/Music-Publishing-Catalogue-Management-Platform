import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";
import {PRO} from "../../zshared/enums/pro";

@Component({
  selector: 'app-artist-edit',
  templateUrl: './artist-edit.component.html',
  styleUrls: ['./artist-edit.component.css']
})
export class ArtistEditComponent implements OnInit {
  id: number | undefined;
  editMode = false;
  artistForm: FormGroup | undefined;
  pro: PRO | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
