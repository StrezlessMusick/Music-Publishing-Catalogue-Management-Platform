import {Track} from "./track";
import {Project} from "./project";
import {PRO} from "../enums/pro";

export interface Artist {
  id: number;
  artistName: string;
  artistImageUrl: string;
  pro: PRO;
  proIPI: string;
  artistTracks: Track[];
  artistProjects: Project[];
}
