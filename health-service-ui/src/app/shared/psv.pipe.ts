import {Pipe, PipeTransform} from "@angular/core";

/**
 * Pipe separated values
 * convert any delimited string to pipe delimited string
 */
@Pipe({
  name: 'psv'
})
export class PsvPipe implements PipeTransform {
  transform(value: string, separator: string): string {
    let temp = value.split(separator).map(x => x.trim())
    return temp.join(' | ');
  }

}
