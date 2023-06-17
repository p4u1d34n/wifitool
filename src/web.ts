import { WebPlugin } from '@capacitor/core';

import type { wifitoolPlugin } from './definitions';

export class wifitoolWeb extends WebPlugin implements wifitoolPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
