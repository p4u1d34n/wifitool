export interface wifitoolPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
