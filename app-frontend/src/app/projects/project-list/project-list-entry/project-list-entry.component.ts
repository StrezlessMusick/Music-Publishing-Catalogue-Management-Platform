import {Component, Input, OnInit} from '@angular/core';
import {Project} from "../../../zshared/interfaces/project";

@Component({
  selector: 'app-project-list-entry',
  templateUrl: './project-list-entry.component.html',
  styleUrls: ['./project-list-entry.component.css']
})
export class ProjectListEntryComponent implements OnInit {
  @Input() project: Project;

  constructor() { }

  ngOnInit(): void {
  }

}
