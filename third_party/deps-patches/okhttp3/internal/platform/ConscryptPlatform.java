/*
 * Racoon AgroMapper build: Conscrypt is not bundled, so this platform is never available.
 * (Original file: Copyright (C) 2014 Square, Inc., Apache License 2.0.)
 */
package okhttp3.internal.platform;

public class ConscryptPlatform extends Platform {
  private ConscryptPlatform() {}
  public static ConscryptPlatform buildIfSupported() { return null; }
}
